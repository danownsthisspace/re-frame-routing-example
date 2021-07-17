(ns routes-example.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [routes-example.events :as events]
   [routes-example.routes :as routes]
   [routes-example.views :as views]
   [routes-example.config :as config]
   [routes-example.users-index.views]
   [routes-example.user-view.views]))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (routes/start!)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
