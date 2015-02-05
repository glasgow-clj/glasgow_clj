(ns glasgow-clj.handler
  (:require [clojure.java.io :as io]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [net.cgrand.enlive-html :as html]))

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(defn get-tweets []
  (html/select (fetch-url "https://twitter.com/glasgow_clj") [:div.GridTimeline-items]))

(html/deftemplate homepage-template "homepage.html"
  []
  [:div.tweets] (constantly (get-tweets)))

(homepage-template)

(defroutes app-routes
  (GET "/" [] (homepage-template))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
