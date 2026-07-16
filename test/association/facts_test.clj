(ns association.facts-test
  (:require [clojure.test :refer [deftest is]]
            [association.facts :as facts]))

(deftest rederiforbundet-has-spec-basis
  (let [sb (facts/spec-basis "rederiforbundet")]
    (is (= 2 (count sb)))
    (is (every? #(= "5010" (:association-rule/isic %)) sb))
    (is (every? #(= "NOR" (:association-rule/country %)) sb))))

(deftest unknown-association-has-no-spec-basis
  (is (nil? (facts/spec-basis "aba")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["rederiforbundet" "aba"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["aba"] (:missing-associations c)))))

(deftest by-topic-filters
  (is (= ["rederiforbundet.founding-1909"]
         (mapv :association-rule/id (facts/by-topic "rederiforbundet" :governance))))
  (is (empty? (facts/by-topic "rederiforbundet" :labor)))
  (is (empty? (facts/by-topic "aba" :governance))))
