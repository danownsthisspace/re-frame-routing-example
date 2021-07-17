(ns routes-example.user-view.views
  (:require [re-frame.core :as re-frame]
            [routes-example.routes :as routes]
            [routes-example.user-view.subs :as subs]
            [routes-example.subs :as route-subs]))

(defn user-view []
  (let [route-params @(re-frame/subscribe [::route-subs/route-params])
        user @(re-frame/subscribe [::subs/user (:id route-params)])]
    [:div (str "The selected user is " (:name user))]))

(defmethod routes/panels :user-view-panel [] [user-view])