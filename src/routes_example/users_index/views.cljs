(ns routes-example.users-index.views
  (:require [re-frame.core :as re-frame]
            [routes-example.users-index.subs :as subs]
            [routes-example.routes :as routes]
            [routes-example.events :as events]))

(defn users-index []
  (let [users @(re-frame/subscribe [::subs/users])]
    [:div
     [:h1 "User List"]
     (map (fn [user] [:div {:key (:id user)
                            :on-click #(re-frame/dispatch [::events/navigate [:user-view :id (:id user)]])} (:name user)]) users)]))

(defmethod routes/panels :users-index-panel [] [users-index])