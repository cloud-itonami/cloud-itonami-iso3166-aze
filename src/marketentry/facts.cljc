(ns marketentry.facts
  "Per-jurisdiction public-procurement market-entry regulatory catalog
  -- the G2-style spec-basis table the Market-Entry Compliance Governor
  checks every `:jurisdiction/assess` proposal against ('did the advisor
  cite an OFFICIAL public source for this jurisdiction's requirements,
  or did it invent one?').

  Azerbaijan's real market-entry surface (WebFetch-verified 2026-07-21,
  see each entry's own citation): the etender.gov.az Unified Internet
  Portal for State Procurement (Dövlət satınalmaları üzrə vahid internet
  portalı), mandatory under the Law of the Republic of Azerbaijan 'On
  State Procurement' (Dövlət satınalmaları haqqında, No. 988-VIQ,
  14 July 2023, in force from 1 January 2024, replacing No. 245-IIQ of
  27 December 2001) for ALL procurements regardless of value or method --
  administered by the State Agency for Antimonopoly and Consumer Market
  Control under the President of the Republic of Azerbaijan (formerly a
  State Service under the Ministry of Economy until an August 2024
  presidential reorganization elevated it to a directly-presidential
  agency; it absorbed the original, now-dissolved, State Procurement
  Agency's functions back in 2016) -- business/tax registration and the
  VÖEN (Vergi ödəyicisinin eyniləşdirmə nömrəsi / Taxpayer Identification
  Number) via the State Tax Service under the Ministry of Economy, which
  performs unified state registration of a commercial legal entity AND
  VÖEN issuance in the SAME act (since a 2016 reform merged the two).

  Verified, and worth stating explicitly because the task asked us to
  check rather than assume: ASAN xidmət (the 'ASAN Service' one-stop-shop
  network, run by the State Agency for Public Service and Social
  Innovations under the President) genuinely IS a real channel for
  business/procurement-adjacent registration specifically -- its own
  service catalog (asan.gov.az) lists 'state registration of
  locally-invested commercial entities', 'state registration of
  foreign-invested commercial entities' and 'state registration of
  foreign commercial representative offices/branches' as services
  delivered at its centers. But ASAN xidmət is a physical/electronic
  INTAKE CHANNEL, not the registering legal authority itself -- the
  underlying state registration + VÖEN issuance is a State Tax Service
  act under tax legislation, submittable either at an ASAN xidmət center
  or directly through the State Tax Service's own e-services. This
  catalog cites the State Tax Service as `:corporate-number-owner-
  authority`, not ASAN, and mentions ASAN only as an evidence-item
  filing channel -- never claiming ASAN itself issues the VÖEN.

  This iteration's research also turned up a genuinely NEW (relative to
  the pre-2024 law) Azerbaijan-specific mechanism: the etibarsız
  təchizatçılar reyestri (unreliable-supplier registry), maintained by
  the State Agency for Antimonopoly and Consumer Market Control per
  Cabinet of Ministers Decision No. 492 (30 December 2023) and Law
  No. 988-VIQ -- a supplier entered on it is barred from further state-
  procurement participation. `marketentry.governor`'s flagship check
  independently verifies an engagement's own declared registry-membership
  flag rather than trusting it. See `unreliable-supplier-spec-basis`.

  Deliberately NOT claimed: a mandatory resident/domestic-representative
  requirement for public-tender participation specifically (the kind JPN
  and BGR's catalogs document for their own jurisdictions). This
  iteration's research found real evidence of a SEPARATE, broader
  concept -- foreign commercial representative offices/branches
  registering in Azerbaijan generally -- but no comparably strong,
  WebFetch-verified evidence that public-procurement participation itself
  REQUIRES one. Rather than force JPN/BGR's rep-check shape onto content
  this iteration could not honestly verify, `rep-spec-basis` returns nil
  for AZE: no `:rep-owner-authority` key is set in this entry, matching
  this catalog's honest-coverage discipline.

  Coverage is reported HONESTLY (see `coverage`): a jurisdiction not in
  this table has NO spec-basis, full stop -- the advisor must not
  fabricate one, and the governor holds if it tries.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit.
  AZE deliberately carries NO `:rep-owner-authority` -- see the
  namespace docstring's honest-scope-narrowing note. `:unreliable-
  supplier-owner-authority` / `:unreliable-supplier-legal-basis` /
  `:unreliable-supplier-provenance` ground this vertical's flagship
  governor check (`unreliable-supplier-spec-basis`)."
  {"AZE" {:name "Azerbaijan"
          :owner-authority "Azərbaycan Respublikasının Prezidenti yanında Antiinhisar və İstehlak Bazarına Nəzarət Agentliyi (State Agency for Antimonopoly and Consumer Market Control under the President of the Republic of Azerbaijan) / etender.gov.az (Dövlət satınalmaları üzrə vahid internet portalı -- Unified Internet Portal for State Procurement)"
          :legal-basis "\"Dövlət satınalmaları haqqında\" Azərbaycan Respublikasının Qanunu (Law of the Republic of Azerbaijan 'On State Procurement'), No. 988-VIQ, adopted 14.07.2023, in force from 01.01.2024 (replaced No. 245-IIQ of 27.12.2001) -- all procurements, regardless of value or method, are required to be conducted through the Unified Internet Portal"
          :national-spec "etender.gov.az supplier/participant profile registration and tender participation via the Unified Internet Portal for State Procurement"
          :provenance "https://etender.gov.az/"
          :required-evidence ["Dövlət Vergi Xidməti dövlət qeydiyyatı şəhadətnaməsi / VÖEN qeydiyyat qeydi (State Tax Service state-registration certificate + VÖEN taxpayer-ID record)"
                              "etender.gov.az təchizatçı/iştirakçı profili qeydiyyat qeydi (Unified Internet Portal supplier/participant-profile registration record)"
                              "Kommersiya hüquqi şəxsin dövlət qeydiyyatı qeydi -- ASAN xidmət mərkəzləri və ya Dövlət Vergi Xidmətinin elektron xidmətləri vasitəsilə (commercial legal-entity state-registration record, filed via an ASAN xidmət service centre or the State Tax Service's own e-services)"
                              "Səlahiyyətli nümayəndə təsdiqi (authorized-representative confirmation record)"]
          :corporate-number-owner-authority "Dövlət Vergi Xidməti (State Tax Service, under the Ministry of Economy of the Republic of Azerbaijan)"
          :corporate-number-legal-basis "VÖEN (Vergi ödəyicisinin eyniləşdirmə nömrəsi / Taxpayer Identification Number) -- a permanent 10-digit code the State Tax Service assigns simultaneously with unified state registration of a commercial legal entity"
          :corporate-number-provenance "https://www.taxes.gov.az/en"
          :unreliable-supplier-owner-authority "Azərbaycan Respublikasının Prezidenti yanında Antiinhisar və İstehlak Bazarına Nəzarət Agentliyi (State Agency for Antimonopoly and Consumer Market Control)"
          :unreliable-supplier-legal-basis "Nazirlər Kabinetinin 30.12.2023 tarixli 492 nömrəli Qərarı ilə təsdiq edilmiş Qaydalar (Cabinet of Ministers Decision No. 492, 30.12.2023) + \"Dövlət satınalmaları haqqında\" Qanunu No. 988-VIQ -- maintains the etibarsız təchizatçılar reyestri (unreliable-supplier registry); registry membership bars further state-procurement participation"
          :unreliable-supplier-provenance "https://competition.gov.az/az/page/haqqimizda/fealiyyet-istiqametleri/dovlet-satinalmalari"}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-aze R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis
  "The jurisdiction's representative-related requirement map, or nil when
  this catalog has no such regime. For AZE this is deliberately nil --
  see the `catalog` docstring's honest-scope-narrowing note."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis
  "The jurisdiction's corporate-number / tax-id regime, or nil."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn unreliable-supplier-spec-basis
  "The jurisdiction's unreliable-supplier-registry regime, or nil.
  For AZE this is real and current -- the flagship check this vertical
  adds is grounded here."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:unreliable-supplier-owner-authority sb)
      (select-keys sb [:unreliable-supplier-owner-authority
                       :unreliable-supplier-legal-basis
                       :unreliable-supplier-provenance]))))
