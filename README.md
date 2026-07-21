# cloud-itonami-iso3166-aze

Open ISO 3166 Blueprint for **AZE**: Republic of Azerbaijan --
**`:implemented`**.

This repository designs **and implements** a forkable OSS business for
an independent public-sector market-entry consultant: an already-
incorporated operator (e.g. a `cloud-itonami-cofog-{code}`,
`cloud-itonami-isco-{code}`, `cloud-itonami-unspsc-{segment}` or
`cloud-itonami-{ISIC}` blueprint fork) gets a Compliance Advisor +
independent **Market-Entry Compliance Governor** to navigate public-
procurement registration, local business/tax registration, and
regulatory-compliance rules in Azerbaijan, so the operator can win and
service a government contract without hiring a full in-house compliance
department.

## Official surface

- Procurement: etender.gov.az -- the Unified Internet Portal for State
  Procurement (Dövlət satınalmaları üzrə vahid internet portalı,
  `https://etender.gov.az/`), mandatory for all procurements regardless
  of value or method under the Law "On State Procurement" (Dövlət
  satınalmaları haqqında, No. 988-VIQ, 14.07.2023, in force from
  01.01.2024), administered by the **State Agency for Antimonopoly and
  Consumer Market Control under the President of the Republic of
  Azerbaijan** (`https://competition.gov.az/`, branded "Antiinhisar" --
  it absorbed the original, since-dissolved State Procurement Agency's
  functions in 2016, and was itself elevated from a Ministry-of-Economy
  "State Service" to a directly-presidential "State Agency" in August
  2024).
- Business registration + tax: the **State Tax Service** (under the
  Ministry of Economy, `https://www.taxes.gov.az/`) performs unified
  state registration of a commercial legal entity and issues the VÖEN
  (Vergi ödəyicisinin eyniləşdirmə nömrəsi / Taxpayer Identification
  Number) in the same act. Filable at an **ASAN xidmət** service centre
  (`https://asan.gov.az/`, confirmed: its own catalog lists locally- and
  foreign-invested commercial-entity registration as a service) or
  directly through the State Tax Service's own e-services -- ASAN xidmət
  is the intake channel, the State Tax Service is the registering
  authority.
- Unreliable-supplier registry: the same regulator maintains the
  etibarsız təchizatçılar reyestri (unreliable-supplier registry) per
  Cabinet of Ministers Decision No. 492 (30.12.2023) -- registry
  membership bars further state-procurement participation.

## Implementation (R0)

| Piece | Location |
|---|---|
| Actor namespaces | `src/marketentry/*` |
| Governor | `:market-entry-compliance-governor` |
| Ops | `:engagement/intake` · `:jurisdiction/assess` · `:filing/draft` · `:filing/submit` |
| Flagship HARD check | `unreliable-supplier-listed` (etibarsız təchizatçılar reyestri membership, independently recomputed -- see `docs/adr/0001-architecture.md`) |
| Compliance catalog | `src/statute/facts.cljc` -- Civil Code, Labour Code, Law on Personal Data |
| Tests | `clojure -M:dev:test` (34 tests / 108 assertions, including the pre-existing culture-catalog suite) |
| Demo | `clojure -M:dev:run` |
| Architecture ADR | [`docs/adr/0001-architecture.md`](docs/adr/0001-architecture.md) |

`:filing/submit` is never in any phase's `:auto` set -- human sign-off
is structural, not a rollout milestone.

## No robotics premise -- digital/data service exemption

Market-entry and procurement-compliance navigation is a pure data/software
service with no physical-domain work (portal registration, document
checklists, regulatory-change monitoring) -- the same exemption class as
`cloud-itonami-6310` (HR SaaS replacement) and `cloud-itonami-gtin-*`.
`blueprint.edn` sets `:itonami.blueprint/robotics false` and
`:required-technologies` lists only real capabilities (`:identity`,
`:forms`, `:dmn`, `:bpmn`, `:audit-ledger`), no `:robotics`.

## Core Contract

```text
operator intake + prior filing history
        |
        v
Compliance Advisor -> Market-Entry Compliance Governor -> filing draft, or human sign-off
        |
        v
gated portal registration / filing submission + audit ledger
```

No automated proposal can submit a portal registration or filing the
governor refuses, suppress a compliance record, or claim a legal/tax
conclusion the governor has not cleared. `:filing/submit` is never in any
phase's `:auto` set -- it always requires human sign-off.

## What this is NOT

- **Not the government of Azerbaijan.** This blueprint is an independent
  operator the government contracts with or that bids into its
  procurement -- never the government itself, and never an official
  channel.
- **Not legal or tax advice.** Every regulatory claim must cite the
  official source and route final filings to Azerbaijani-licensed
  counsel or a registered agent where the law requires licensed
  representation.

## Capability layer

Required capabilities (`blueprint.edn`):

- :identity
- :forms
- :dmn
- :bpmn
- :audit-ledger

See [`docs/business-model.md`](docs/business-model.md) and
[`docs/operator-guide.md`](docs/operator-guide.md).

## License

AGPL-3.0-or-later.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) -- national dishes, protected products, beverages,
crafts, festivals and heritage sites for Azerbaijan:

- `src/culture/facts.cljc` -- the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` -- DataScript schema.
- `data/culture-tx.edn` -- derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis -- never fabricate one.
