# ADR-0001: Architecture — Azerbaijan market-entry compliance actor (`marketentry`)

**Status**: accepted
**Date**: 2026-07-21

## Context

`cloud-itonami-iso3166-aze` was published as a `:blueprint` (docs +
`blueprint.edn` only, then a country-level `culture.facts` catalog in a
separate Wave 1 batch, plus a fleet-standard `deps.edn` backfill) but
carried ZERO `src/marketentry` or `src/statute` content -- its
`:public-sector/market-entry-compliance` domain, declared in
`blueprint.edn`, was unimplemented. This ADR closes that gap, following
the pattern established by `cloud-itonami-iso3166-jpn` (origin) and
continued by `cloud-itonami-iso3166-deu`/`-bgr` (the simpler, no-
`goyoukiki` shape this blueprint also uses -- `blueprint.edn`'s
`:required-technologies` does not list `:ontology`, so this fork skips
the `marketentry.goyoukiki` real-tender-fact bridge JPN carries).

## Decision

Build the full governed-actor architecture for `marketentry`, mirroring
JPN/DEU/BGR's harness verbatim (StateGraph node names, governor hard/
escalate contract, phase 0-3 rollout, `Store` protocol with MemStore +
DatomicStore parity) and researching Azerbaijan's own real market-entry
rules from scratch for the country-specific content:

- **Store**: `marketentry.store`, MemStore + DatomicStore, proven parity
  via contract test.
- **Registry**: `marketentry.registry`, pure DRAFT-certificate
  construction via `unsigned-certificate`, jurisdiction-scoped sequence
  numbering (`AZE-DFT-000000`, `AZE-SUB-000000`). Unlike BGR's sibling
  registry, this one has NO numeric-threshold recompute function --
  AZE's flagship check (below) is a plain registry-membership boolean,
  not a computed formula, so there is nothing for `registry.cljc` to
  add here. Documented as a deliberate difference, not an omission.
- **Governor**: `:market-entry-compliance-governor` (family keyword from
  `blueprint.edn`).
- **Entity shape**: `engagement`, sequential draft -> submit on the same
  record. `high-stakes` = `#{:actuation/draft-filing
  :actuation/submit-filing}`.
- **Phase**: 0->3; `:filing/draft` and `:filing/submit` NEVER auto-
  commit at any phase.

### Research findings that shaped the country-specific content

- **Procurement portal + regulator**: etender.gov.az, the Unified
  Internet Portal for State Procurement, is administered by the **State
  Agency for Antimonopoly and Consumer Market Control under the
  President of the Republic of Azerbaijan** -- confirmed directly from
  the agency's own site (competition.gov.az, branded "Antiinhisar").
  This agency absorbed the original, since-dissolved **State Procurement
  Agency**'s functions in 2016, and was itself elevated from a Ministry-
  of-Economy-subordinate "State Service" to a directly-presidential
  "State Agency" in an August 2024 reorganization -- both confirmed via
  Wikipedia cross-reference against the agency's own site, and worth
  recording precisely because an agent naively citing "the State
  Procurement Agency" (the name most search results surface first) would
  be citing a body that no longer exists in that form.
- **Governing law**: Law "On State Procurement" (Dövlət satınalmaları
  haqqında), No. 988-VIQ, 14.07.2023, in force from 01.01.2024,
  replacing No. 245-IIQ (27.12.2001). A law-firm secondary analysis
  (e-legal.az) confirmed the new law's mandatory-portal-use provision
  ("all procurements, regardless of value or method, conducted through
  the unified portal") in more sweeping terms than the old law's
  threshold-gated version -- this iteration's WebFetch tool could not
  render e-qanun.az's own article-level text directly (it is a
  JavaScript SPA), so confidence on this specific provision is MODERATE
  (cross-referenced across two independent secondary sources plus the
  regulator's own site, not read verbatim from the primary legislative
  text).
- **Business/tax registration**: the **State Tax Service** (under the
  Ministry of Economy) performs UNIFIED state registration of a
  commercial legal entity and VÖEN (Taxpayer Identification Number)
  issuance in the same act -- confirmed directly from taxes.gov.az.
- **ASAN xidmət -- verified, not assumed**: the task explicitly asked
  whether ASAN xidmət (the well-known "ASAN Service" one-stop-shop) is
  actually the relevant channel for business/procurement registration
  specifically, rather than assuming it. Its own service catalog
  (asan.gov.az) DOES list "state registration of locally-invested
  commercial entities", "state registration of foreign-invested
  commercial entities" and "state registration of foreign commercial
  representative offices/branches" as services delivered at its
  centers -- so yes, genuinely relevant, confirmed via ASAN's own site.
  But ASAN xidmət is an intake/service-delivery CHANNEL, not the
  registering legal authority -- the underlying registration + VÖEN
  issuance remains a State Tax Service act under tax legislation. This
  catalog cites the State Tax Service as `:corporate-number-owner-
  authority`, and mentions ASAN only inside one `:required-evidence`
  item's description as a filing channel, never as the authority itself.

### Flagship HARD check: `unreliable-supplier-listed` (genuinely new shape)

JPN's flagship check (`japan-resident-rep-missing`) and BGR's
(`tax-arrears-exceeds-threshold`) are, respectively, a CONDITIONAL
boolean (gated on a `:requires-X?` flag) and an UNCONDITIONAL numeric
threshold recompute. Researching Azerbaijan's own procurement law did
not turn up comparably strong, WebFetch-verified evidence of a
resident/domestic-representative mandate specific to public-procurement
participation (as distinct from the separate, broader question of
foreign commercial representative offices/branches registering in
Azerbaijan generally) -- so rather than force JPN/BGR's rep-check shape
onto content that does not honestly support it, `rep-spec-basis` returns
nil for AZE (see `marketentry.facts`'s own docstring).

Instead, this fork's own research surfaced a genuinely Azerbaijan-
specific mechanism confirmed directly from the regulator's own site
(competition.gov.az): the **etibarsız təchizatçılar reyestri**
(unreliable-supplier registry), which the State Agency for Antimonopoly
and Consumer Market Control maintains per Cabinet of Ministers Decision
No. 492 (30.12.2023) and Law No. 988-VIQ -- a supplier entered on it is
barred from further state-procurement participation. This concept is
itself NEW relative to the pre-2024 law (the 2023 law's own drafting
history introduces "unreliable supplier" as a defined term for the first
time). `unreliable-supplier-listed-violations` independently verifies
the engagement's own declared `:on-unreliable-supplier-registry?` flag
is not true, UNCONDITIONALLY (every `:filing/submit`, no `:requires-X?`
gate -- the same "always applies" shape BGR's check introduced), but as
a boolean registry-membership check rather than a numeric formula -- a
third distinct check SHAPE for this family (conditional-boolean /
unconditional-numeric / unconditional-boolean), reflecting what this
country's own law actually and verifiably supports rather than a
reflexive per-country copy of either predecessor's shape.

This iteration's confidence on this check is HIGH for the check's
existence, legal basis (Cabinet Decision No. 492) and consequence
(participation bar) -- all confirmed directly from the regulator's own
official page -- but LOWER on the exact listing criteria/appeal/duration
mechanics, which that same page did not detail and this iteration's time
budget did not extend to tracking down in Cabinet Decision No. 492's own
full text. The governor check and demo data therefore model registry
membership as a ground-truth boolean the engagement declares (mirroring
how sibling actors model `:requires-eik?`/`:eik-verified?` etc.), not a
claim about what specifically causes listing.

### Other HARD checks (all unoverridable)

1. **spec-basis** -- never invent a jurisdiction's market-entry
   requirements (`marketentry.facts` G2 catalog: etender.gov.az, State
   Tax Service / VÖEN, for AZE).
2. **evidence-incomplete** -- draft/submit require a full assessment
   checklist on file.
3. **unreliable-supplier-listed** -- see above (FLAGSHIP).
4. **engagement-fee-mismatch** -- recompute `base-fee + monthly-rate ×
   monitoring-months` (ground-truth-recompute discipline).
5. **voen-unverified** -- conditional on `:requires-voen?` (VÖEN /
   Vergi ödəyicisinin eyniləşdirmə nömrəsi regime, assigned by the State
   Tax Service on unified state registration).
6. **already-drafted / already-submitted** -- dedicated booleans, never
   a `:status` value.

### `statute.facts` (second, orthogonal catalog)

Three Azerbaijani statutes: Civil Code (Mülki Məcəllə -- Azerbaijan has
no standalone Commerce Act separate from its Civil Code the way
Bulgaria/Germany do, a genuine structural difference reported honestly
rather than force-fit), Labour Code (Əmək Məcəlləsi), and the Law "On
Personal Data" (Fərdi məlumatlar haqqında). The Labour Code and Personal
Data Law were fetched and read directly via `frameworks.e-qanun.az`
(Azerbaijan's official legal-database mirror subdomain, HIGH
confidence); the Civil Code's own container page on that subdomain could
not be located this iteration (several `c_c_<n>` URLs probed turned out
to be other codes), so its citation instead points to the Central Bank
of Azerbaijan's own official mirror of the Civil Code text (MODERATE
confidence -- a real government body's own hosted copy, confirmed, but
not the primary legislative gazette). The Personal Data Law itself
splits oversight across multiple bodies by law rather than naming one
dedicated authority (unlike Bulgaria's single CPDP) -- reported honestly
in the catalog's own docstring rather than picking one body and calling
it "the" regulator.

## Consequences

- `src/` now genuinely exists with real, tested, WebFetch-cited content
  for this blueprint's declared domain (`:public-sector/market-entry-
  compliance`) -- moves this repo's `manifest/itonami-fleet-audit.edn`
  `:prod-ready?` signal from `:stub` to `:active`.
- The existing `culture.facts` catalog (Wave 1, unrelated batch) is
  untouched, and its own test suite continues to pass alongside the new
  `marketentry`/`statute` suites (34 tests / 108 assertions total, 0
  failures).
- A pre-existing, unrelated copy-paste bug was found and fixed during
  this work: `CONTRIBUTING.md`/`GOVERNANCE.md` still referenced
  `cloud-itonami-iso3166-khm` (Cambodia) and "Cambodia"/"Cambodian" text
  instead of this repo's own id and Azerbaijan -- the same class of bug
  the BGR iteration of this loop found and fixed for its own repo.
- Sibling country blueprints can continue forking JPN/DEU/BGR/AZE and
  swapping in their own genuinely-researched `marketentry.facts` /
  `statute.facts` content and whichever flagship check their own law
  actually supports -- this ADR is itself evidence that the flagship
  check should be chosen from real research, not copied by rote, and
  that honest scope-narrowing (no rep-spec-basis claim for AZE) is a
  legitimate, expected outcome of that research, not a shortfall.
