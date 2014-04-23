(ns vasco.core
  (:require [clojure.string :as string]))

(defn- render
  [template data]
  ;; "/users/:username" + {:username "john"} = "/users/john"
  (letfn [(f [acc [k v]] (string/replace acc (str ":" (name k)) (str v)))]
    (let [m (into {} (vec (map vec (partition 2 data))))]
      (reduce f template m))))

(defn make-path-fn
  [path-template]
  (fn [& data] (render path-template data)))

#+clj
(defmacro defendpoint [name-sym path]
  (let [name (name name-sym)
        x-template-sym (symbol (str name "-template"))
        x-path-sym (symbol (str name "-path"))]
    `(do (def ~x-template-sym ~path)
         (def ~x-path-sym (make-path-fn ~x-template-sym)))))
