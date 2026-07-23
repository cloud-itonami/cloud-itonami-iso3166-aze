(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest aze-has-spec-basis
  (let [sb (facts/spec-basis "AZE")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/corporate-number-spec-basis "AZE")))
    (is (some? (facts/unreliable-supplier-spec-basis "AZE")))))

(deftest aze-rep-spec-basis-is-honestly-absent
  (testing "no verified resident/domestic-representative mandate for AZE public-procurement participation -- deliberately not claimed"
    (is (nil? (facts/rep-spec-basis "AZE")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "AZE")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "AZE" all)))
    (is (not (facts/required-evidence-satisfied? "AZE" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["AZE" "ATL"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))
