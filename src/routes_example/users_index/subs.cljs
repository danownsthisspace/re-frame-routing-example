(ns routes-example.users-index.subs
  (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 ::users
 (fn [db _]
   (:users db)))