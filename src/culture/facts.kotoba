(ns culture.facts
  "Country-level regional-culture catalog for Azerbaijan (AZE) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"AZE"
   [{:culture/id "aze.dish.dushbara"
     :culture/name "Dushbara"
     :culture/name-local "Düşbərə"
     :culture/country "AZE"
     :culture/kind :dish
     :culture/summary "Dumplings of unleavened wheat dough filled with ground meat, popular across Central Asia and the Middle East; the Azerbaijani version, düşbərə, uses smaller dumplings with thicker dough."
     :culture/url "https://en.wikipedia.org/wiki/Joshpara"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aze.dish.qutab"
     :culture/name "Qutab"
     :culture/name-local "Qutab"
     :culture/country "AZE"
     :culture/kind :dish
     :culture/summary "Crescent-shaped stuffed flatbread cooked on a griddle, a traditional dish in Azerbaijani, Turkmen and Turkish cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Qutab"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aze.dish.piti"
     :culture/name "Piti"
     :culture/country "AZE"
     :culture/kind :dish
     :culture/summary "Mutton and chickpea soup of the cuisines of the South Caucasus, particularly associated with Azerbaijan, prepared in the oven in individual glazed crocks."
     :culture/url "https://en.wikipedia.org/wiki/Piti_(food)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aze.dish.dolma"
     :culture/name "Dolma"
     :culture/country "AZE"
     :culture/kind :dish
     :culture/summary "Family of stuffed dishes shared across former Ottoman cuisines; dolma making in Azerbaijan was included in the UNESCO Intangible Cultural Heritage Lists in 2017."
     :culture/url "https://en.wikipedia.org/wiki/Dolma"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aze.craft.azerbaijani-carpet"
     :culture/name "Azerbaijani carpet"
     :culture/name-local "Azərbaycan xalçası"
     :culture/country "AZE"
     :culture/kind :craft
     :culture/summary "Traditional handmade carpet of Azerbaijan with region-specific patterns, designated a UNESCO Masterpiece of Intangible Heritage in 2010."
     :culture/url "https://en.wikipedia.org/wiki/Azerbaijani_rug"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aze.festival.novruz"
     :culture/name "Novruz"
     :culture/name-local "Novruz bayramı"
     :culture/country "AZE"
     :culture/kind :festival
     :culture/summary "Spring equinox festival marking the arrival of spring, an official public holiday in Azerbaijan celebrated with bonfires, family gatherings and traditional foods."
     :culture/url "https://en.wikipedia.org/wiki/Novruz_in_Azerbaijan"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aze.heritage.old-city-baku"
     :culture/name "Old City of Baku"
     :culture/name-local "İçərişəhər"
     :culture/country "AZE"
     :culture/kind :heritage
     :culture/summary "Walled medieval core of Baku with mosques, palaces and caravanserais, the first location in Azerbaijan classified as a UNESCO World Heritage Site (2000)."
     :culture/url "https://en.wikipedia.org/wiki/Old_City_(Baku)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aze.heritage.gobustan"
     :culture/name "Gobustan State Reserve"
     :culture/name-local "Qobustan"
     :culture/country "AZE"
     :culture/kind :heritage
     :culture/summary "Protected reserve southwest of Baku containing over 6,000 prehistoric rock carvings; the Gobustan Rock Art Cultural Landscape was inscribed as a UNESCO World Heritage Site in 2007."
     :culture/url "https://en.wikipedia.org/wiki/Gobustan_State_Reserve"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-aze culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "AZE"))
                 " AZE entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
