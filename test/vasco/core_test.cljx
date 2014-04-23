(ns vasco.core-test
  (:require #+clj [clojure.test :refer :all]
            #+cljs [cemerick.cljs.test :as t :refer-macros [deftest is testing]]
            #+clj [vasco.core :refer [defendpoint]]
            #+cljs [vasco.core :refer-macros [defendpoint]]))

(defendpoint test-home "/")
(defendpoint test-test "/test")
(defendpoint test-profile "/users/:username")
(defendpoint test-abc "/a/:a/b/:b/c/:c")

(deftest test-templates
  (is (= "/" test-home-template))
  (is (= "/test" test-test-template))
  (is (= "/users/:username" test-profile-template))
  (is (= "/a/:a/b/:b/c/:c" test-abc-template)))

(deftest test-paths
  (is (= "/" (test-home-path)))
  (is (= "/test" (test-test-path)))
  (is (= "/users/john" (test-profile-path :username "john")))
  (is (= "/a/1/b/foo/c/:d" (test-abc-path :a 1 :b "foo" :c :d))))

;;(run-tests)

