(ns react-navigation.core
  (:require [reagent.core :as r :refer [atom]]))



(def react-navigation (js/require "react-navigation"))

(def add-navigation-helpers (.-addNavigationHelpers react-navigation))
(def tab-navigator (.-TabNavigator react-navigation))

(def stack-navigator-rn (.-StackNavigator react-navigation))
(defn stack-navigator [routes config]
  (stack-navigator-rn (clj->js routes) (clj->js config)))

(def drawer-navigator-rn (.-DrawerNavigator react-navigation))
(defn drawer-navigator [routes config]
  (drawer-navigator-rn (clj->js routes) (clj->js config)))
(def drawer-items-rn (.-DrawerItems react-navigation))
(def drawer-items (r/adapt-react-class drawer-items-rn))

(defn nav-wrapper [component title]
  (let [comp (r/reactify-component
               (fn [{:keys [navigation]}]
                 [component (-> navigation .-state js->clj)]))]
    (aset comp "navigationOptions" #js {"title" title})
    comp))

(defn nav-wrapper-utils [component options]
  (let [comp (r/reactify-component
               (fn [{:keys [navigation]}]
                 [component (-> navigation .-state js->clj)]))]
    (aset comp "navigationOptions" (clj->js options))
    comp))