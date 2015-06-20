(ns just-wrapped-random-page.core
  (require [clj-http.client :as client]
           [clojure.xml :as xml]))

(def site-url
  "http://justwrapped.me/sitemap.xml")

(defn parse-response-body [url]
  (xml/parse (java.io.ByteArrayInputStream. (.getBytes (:body (client/get url))))))

(defn extract-links [body]
  (map #(:content %)
       (map #(first %)
            (map #(:content %) (:content body)))))


(defn get-random-link [links]
  (first (rand-nth links)))

(defn -main []
  (println (get-random-link (extract-links (parse-response-body site-url)))))
