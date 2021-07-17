(ns routes-example.user-view.subs
  (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 ::user
 (fn [db [_ user-id]]
   (first (filter (fn [u] (= (:id u) (int user-id))) (:users db)))))