(ns compost.handler
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [compost.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]
            [compost.users :as users]
            [clojure.data.json :as json]))

(def mount-target
  [:div#app
      [:h3 "ClojureScript has not been compiled!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))])

(def index-page
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/app.js")]))


(defroutes routes
  (GET "/" 
       [] index-page)

  (GET "/users"
       [] "goo")

  (POST "/users"
        request (users/new
                 (json/read-str (slurp (:body request))
                 :key-fn keyword)))

  (GET "/messages"
       params params)

  (POST "/messages"
        params params)

  (GET "/questionnaires"
       params params)

  (POST "/questionnaires"
        params params)

  (POST "/tokens"
        params)

  (resources "/")

  (not-found "404"))

(def app (wrap-middleware #'routes))
