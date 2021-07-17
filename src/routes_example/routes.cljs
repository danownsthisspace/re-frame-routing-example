(ns routes-example.routes
  (:require
   [bidi.bidi :as bidi]
   [pushy.core :as pushy]
   [re-frame.core :as re-frame]
   [routes-example.events :as events]))

(defmulti panels identity)
(defmethod panels :default [] [:div "No panel found for this route."])

(def routes
  (atom
   ["/" {""      :home
         "about" :about
         "users" {"" :users-index
                  ["/" :id] :user-view}}]))

(defn parse
  [url]
  (bidi/match-route @routes url))

(parse "/users/1")

(defn url-for
  [& args]
  (apply bidi/path-for (into [@routes] args)))

(defn dispatch
  [route]
  (let [panel (keyword (str (name (:handler route)) "-panel"))]
    #_(re-frame/dispatch [::events/set-active-panel panel])
    (re-frame/dispatch [::events/set-route {:route route :panel panel}])))

(defonce history
  (pushy/pushy dispatch parse))

(defn navigate!
  [handler]
  (pushy/set-token! history (apply url-for handler)))

(defn start!
  []
  (pushy/start! history))

(re-frame/reg-fx
 :navigate
 (fn [handler]
   (navigate! handler)))
