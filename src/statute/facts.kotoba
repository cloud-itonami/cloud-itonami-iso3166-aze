(ns statute.facts
  "General-law compliance catalog for Azerbaijan (AZE) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company operating in this jurisdiction must generally track for
  compliance. Mirrors cloud-itonami-iso3166-jpn/-deu/-bgr's `statute.facts`
  (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL Azerbaijani government-hosted URL --
  never fabricated (WebFetch-verified 2026-07-21, content actually
  fetched and read, not taken from a secondary summary):

  - Azerbaijan's own consolidated-law portal, e-qanun.az, is a
    JavaScript single-page application whose article-level content this
    iteration's WebFetch tool could not render directly (only the page
    header/logo loaded). Its `frameworks.e-qanun.az` mirror subdomain
    DOES render plain HTML for many, but not all, laws/codes -- it
    rendered the Labour Code and the Law on Personal Data directly (both
    cited below with their `frameworks.e-qanun.az` URL, HIGH
    confidence: full text content was actually read, not just metadata).
    It did NOT have a working URL this iteration could locate for the
    Civil Code's own container page (several `c_c_<n>` URLs were probed
    and turned out to be OTHER codes -- Air Code, Housing Code, Land
    Code, etc. -- not the Civil Code); rather than guess further or
    fabricate an e-qanun.az URL, the Civil Code entry below instead cites
    the Central Bank of Azerbaijan's own official mirror
    (cbar.az/law-169), a real Azerbaijani state body's own site
    confirmed (WebFetch) to host the Civil Code's full text -- MODERATE
    confidence on this one specifically: the adoption/effective dates
    were cross-confirmed via a Constitutional Court decision citing the
    code and multiple independent search results, but the CBAR page's
    own text could not be fully read in one fetch (content was
    truncated by page length, not a failure to find the right page).
  - Azerbaijan does not have a single, standalone Commerce Act separate
    from its Civil Code the way Bulgaria/Germany do -- Azerbaijan's own
    Civil Code (Mülki Məcəllə) itself contains the general provisions on
    commercial legal entities, so it is cited here for the
    corporate-governance/incorporation topic instead of a
    same-shaped-but-nonexistent standalone commercial code. This is a
    genuine structural difference between jurisdictions, reported
    honestly rather than force-fit.
  - The Law 'On Personal Data' (No. 998-IIIQ, 11.05.2010) itself does
    NOT name a single dedicated data-protection authority by name --
    it uses the generic phrase 'müvafiq icra hakimiyyəti orqanı'
    (relevant executive authority) for its state-registry/oversight
    functions, delegating the actual body to subsequent Cabinet of
    Ministers decisions (a September 2010 decision assigns supervisory
    duties across SEVEN state bodies; an August 2010 decision assigns
    the information-systems state-registry function specifically to the
    Ministry of Digital Development and Transport). Unlike Bulgaria's
    single dedicated CPDP, Azerbaijan's data-protection oversight is
    genuinely split across multiple bodies by law -- this catalog
    reports that honestly rather than picking one body and calling it
    'the' regulator.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"AZE"
   [{:statute/id "aze.civil-code"
     :statute/title "Azərbaycan Respublikasının Mülki Məcəlləsi (Civil Code of the Republic of Azerbaijan)"
     :statute/jurisdiction "AZE"
     :statute/kind :law
     :statute/law-number "Mülki Məcəllə, qəbul 28.12.1999, qüvvəyə minmə 01.09.2000 (1964-cü il Mülki Məcəlləsini əvəz edir)"
     :statute/url "https://www.cbar.az/law-169/civil-code-of-the-republic-of-aerbaijan"
     :statute/url-provenance :official-central-bank-hosted-code
     :statute/enacted-date "2000-09-01"
     :statute/retrieved-at "2026-07-21"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "aze.labour-code"
     :statute/title "Azərbaycan Respublikasının Əmək Məcəlləsi (Labour Code of the Republic of Azerbaijan)"
     :statute/jurisdiction "AZE"
     :statute/kind :law
     :statute/law-number "Əmək Məcəlləsi, № 618-IQ, 01.02.1999"
     :statute/url "https://frameworks.e-qanun.az/46/f_46943.html"
     :statute/url-provenance :official-e-qanun
     :statute/enacted-date "1999-02-01"
     :statute/retrieved-at "2026-07-21"
     :statute/topic #{:labor :employment}}
    {:statute/id "aze.personal-data-law"
     :statute/title "\"Fərdi məlumatlar haqqında\" Azərbaycan Respublikasının Qanunu (Law of the Republic of Azerbaijan 'On Personal Data')"
     :statute/jurisdiction "AZE"
     :statute/kind :law
     :statute/law-number "№ 998-IIIQ, 11.05.2010"
     :statute/url "https://frameworks.e-qanun.az/19/f_19675.html"
     :statute/url-provenance :official-e-qanun
     :statute/enacted-date "2010-05-11"
     :statute/retrieved-at "2026-07-21"
     :statute/topic #{:data-protection :privacy}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-aze statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "AZE")) " AZE statutes seeded with an "
                 "official government-hosted citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :data-protection)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
