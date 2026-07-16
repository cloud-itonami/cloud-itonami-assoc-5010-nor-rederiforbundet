(ns association.facts
  "Industry rule/history catalog for the Norwegian Shipowners'
  Association (Norges Rederiforbund, NOR) -- a 33rd
  industry-association-level source per ADR-2607141700
  (cloud-itonami-compliance-fact-federation). The FIRST entry aligned
  to ISIC 5010 (sea and coastal water transport), a new industry code
  for this family, and the first Norway-based association entry
  (Norway had previously only appeared on the municipality axis via
  cloud-itonami-municipality-nor-oslo).

  Both entries directly WebFetch-verified against rederi.no (the
  association's own official domain): the 'Vår historie' (Our
  History) page states plainly that the constitutive meeting was held
  15 September 1909, unanimously deciding to establish the
  association; the 'About us' page describes the Thor Heyerdahl
  International Maritime Award, established in 1999, which recognizes
  candidates for exceptional technical innovation and environmental
  work.

  A rule not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "assoc-slug -> vector of self-regulatory rule entries."
  {"rederiforbundet"
   [{:association-rule/id "rederiforbundet.founding-1909"
     :association-rule/title "Norges Rederiforbund founding (Vår historie)"
     :association-rule/association "rederiforbundet"
     :association-rule/isic "5010"
     :association-rule/country "NOR"
     :association-rule/kind :governance-program
     :association-rule/url "https://www.rederi.no/om-oss/historie/"
     :association-rule/url-provenance :official-rederi-no
     :association-rule/established-date "1909-09-15"
     :association-rule/retrieved-at "2026-07-16"
     :association-rule/topic #{:governance}}
    {:association-rule/id "rederiforbundet.thor-heyerdahl-award-1999"
     :association-rule/title "Thor Heyerdahl International Maritime Award (About us)"
     :association-rule/association "rederiforbundet"
     :association-rule/isic "5010"
     :association-rule/country "NOR"
     :association-rule/kind :governance-program
     :association-rule/url "https://www.rederi.no/en/about-us/"
     :association-rule/url-provenance :official-rederi-no
     :association-rule/established-date "1999"
     :association-rule/retrieved-at "2026-07-16"
     :association-rule/topic #{:environment}}]})

(defn spec-basis [assoc-slug] (get catalog assoc-slug))

(defn coverage
  ([] (coverage (keys catalog)))
  ([slugs]
   (let [have (filter catalog slugs)
         missing (remove catalog slugs)]
     {:requested (count slugs)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-5010-nor-rederiforbundet Wave 0 (ADR-2607141700): "
                 (count (get catalog "rederiforbundet")) " rederiforbundet entries "
                 "seeded with an official rederi.no citation. Extend "
                 "`association.facts/catalog`, never fabricate a rule id/url.")})))

(defn by-topic [assoc-slug topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis assoc-slug)))
