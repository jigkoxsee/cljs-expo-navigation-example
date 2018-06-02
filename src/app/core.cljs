(ns app.core
    (:require [reagent.core :as r :refer [atom]]
              [re-frame.core :refer [subscribe dispatch dispatch-sync]]
              [oops.core :refer [ocall]]
              [react-native.core :refer [Alert image view text touchable-opacity]]
              [expo.core :refer [Expo ionicons icons]]
              [app.handlers]
              [app.subs]))

(defn alert [title]
  (.alert Alert title))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
       [image {:source (js/require "./assets/images/cljs.png")
               :style {:width 200
                       :height 200}}]
       [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} @greeting]
       [touchable-opacity
         [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} "Hello"]]
       [ionicons {:name "ios-arrow-down" :size 60 :color "green"}]
       [icons {:name "trash" :size 60 :color "green"}]
       [touchable-opacity {:style {:background-color "#999" :padding 10 :border-radius 5}
                           :on-press #(alert "HELLO!")}
        [text {:style {:color "white" :text-align "center" :font-weight "bold"}} "press me"]]])))

(defn init []
  (dispatch-sync [:initialize-db])
  (ocall Expo "registerRootComponent" (r/reactify-component app-root)))
