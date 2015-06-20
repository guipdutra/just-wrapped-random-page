(ns just-wrapped-random-page.core
  (require [clj-http.client :as client]
           [clojure.xml :as xml]))

(def site-links
  (map #(:content %) (map #(first %) (map #(:content %) (:content (xml/parse (java.io.ByteArrayInputStream. (.getBytes (:body (client/get "http://justwrapped.me/sitemap.xml"))))))))))


(defn get-random-link [links]
  (rand-nth links))

(defn -main []
  (println (get-random-link site-links)))
