# Business Model: Independent Public-Sector Market-Entry & Procurement Compliance Service — Republic of Azerbaijan

## Classification

- Repository: `cloud-itonami-iso3166-aze`
- ISO 3166: `AZE` (Azerbaijan)
- Activity: public-procurement market-entry and ongoing regulatory-
  compliance navigation for an already-incorporated operator
- Social impact: [:sme-market-access :public-spend-transparency :cross-border-friction-reduction]

## Customer

- an already-incorporated `cloud-itonami-cofog-{code}` /
  `cloud-itonami-isco-{code}` / `cloud-itonami-unspsc-{segment}` /
  `cloud-itonami-{ISIC}` operator wanting to bid on an Azerbaijani
  public contract
- a foreign SME or civic-tech vendor entering the public sector in
  Azerbaijan for the first time
- a `cloud-itonami-M6910` client that has just completed incorporation
  and now needs public-sector market access

## Offer

- registration walkthrough for etender.gov.az (the Unified Internet
  Portal for State Procurement, Dövlət satınalmaları üzrə vahid internet
  portalı), mandatory for all procurements regardless of value or method
  under the Law "On State Procurement" (Dövlət satınalmaları haqqında,
  No. 988-VIQ, 14.07.2023), administered by the State Agency for
  Antimonopoly and Consumer Market Control under the President of the
  Republic of Azerbaijan
- business/tax registration checklist: unified state registration of a
  commercial legal entity + VÖEN (Vergi ödəyicisinin eyniləşdirmə
  nömrəsi / Taxpayer Identification Number) issuance via the State Tax
  Service (under the Ministry of Economy) -- filable either at an ASAN
  xidmət service centre (confirmed, its own catalog lists locally- and
  foreign-invested commercial-entity registration as a service) or
  directly through the State Tax Service's own e-services
- unreliable-supplier-registry status check: independent verification
  that the operator is not entered on the etibarsız təchizatçılar
  reyestri (unreliable-supplier registry) the regulator maintains, a
  categorical bar to further state-procurement participation
- ongoing regulatory-change monitoring subscription
- compliance-audit export package for the client's own records

## Revenue

- per-engagement market-entry fee (one-time registration + checklist
  completion)
- recurring regulatory-change monitoring subscription
- compliance-audit export package

## Trust Controls

- any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off (`:filing/submit` is never automated at any phase)
- a false or fabricated regulatory-requirement claim is a HARD hold that
  cannot be overridden by human approval alone -- it must be corrected
  against a cited official source first
- unreliable-supplier-registry membership is a HARD, unconditional hold
  on `:filing/submit` -- the governor independently checks the
  engagement's own declared registry-membership flag rather than
  trusting a claimed status
- this service does **not** provide legal or tax advice; characterization
  and filing on the client's behalf beyond checklist/draft assistance
  routes to Azerbaijani-licensed counsel or a registered agent
- every requirement cites the official portal or regulation, never
  invented -- and where this iteration's research could not verify a
  claim strongly enough (e.g. a resident/domestic-representative mandate
  specific to public-procurement participation), the claim is simply not
  made, rather than guessed

## Boundary with adjacent actors (read before forking)

- **`com-etzhayyim-ooyake`** (etzhayyim/root): read-only civic-wayfinding
  mirror of government structure, non-commercial, barred from acting as
  or for the government (G3 impersonation ban). This blueprint is
  commercial and never claims to be an official channel.
- **`matsurigoto`** (etzhayyim/root): sovereign e-government statecraft --
  literally the government, for etzhayyim's own covenant or an adopting
  nation-state. This blueprint is an independent operator the government
  contracts with or that bids into its procurement -- never the
  government.
- **`com-etzhayyim-toritsugi`** (etzhayyim/root): guides a consenting
  INDIVIDUAL citizen through their OWN procedure, non-profit,
  donation-only. This blueprint's client is a business operator, not an
  individual citizen, and it is commercial.
- **`legal-entity.etzhayyim.com`**: read-only aggregated company-registry
  data, no execution. This blueprint executes (gated) registrations.
- **`cloud-itonami-M6910`**: helps a client BECOME a legal entity
  (incorporation, ISIC 6910) -- a prior, different regulatory phase
  (company law). This blueprint assumes incorporation is already done and
  handles public-procurement market entry (a different regulatory domain).
- **`cloud-itonami-cofog-{code}`**: a jurisdiction-agnostic operator
  template for ONE public function. This blueprint is the orthogonal
  jurisdiction-specific axis -- the two compose (fork a COFOG-function
  blueprint AND this one to operate in Azerbaijan).
