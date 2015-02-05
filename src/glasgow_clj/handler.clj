(ns glasgow-clj.handler
  (:require [clojure.java.io :as io]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (slurp (io/resource "homepage.html")))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
