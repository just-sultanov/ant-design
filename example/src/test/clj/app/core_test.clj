(ns app.core-test
  (:require [clojure.test :as t]
            [app.core :as sut]))

(t/deftest ^:unit square-test
  (t/testing "square test:"
    (t/testing "expected 4"
      (let [expected 4
            actual   (sut/square 2)]
        (t/is (= actual expected))))))
