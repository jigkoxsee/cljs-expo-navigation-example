(ns react-native.core
  (:require [reagent.core :as r :refer [atom]]))


(defn random-color
  []
  (js* "'#'+('00000'+(Math.random()*(1<<24)|0).toString(16)).slice(-6)"))


;; --------------- React Native -----------
(def ReactNative (js/require "react-native"))

(defn get-rn-component [comp-name]
  (r/adapt-react-class  (aget ReactNative comp-name)))


(def activity-indicator (get-rn-component "ActivityIndicator"))
(def Alert (aget ReactNative "Alert"))
(def AppRegistry (aget ReactNative "AppRegistry"))
(def DeviceEventEmitter (aget ReactNative "DeviceEventEmitter"))
(def ^:private flatList (get-rn-component "FlatList"))
(def image (get-rn-component "Image"))
(def keyboard-avoid-view (get-rn-component "KeyboardAvoidingView"))
(def linking (get-rn-component "Linking"))
(def modal (get-rn-component "Modal"))
(def RnImage (aget ReactNative "Image"))
(def scroll-view (get-rn-component "ScrollView"))
(def StatusBar (aget ReactNative  "StatusBar"))
(def text (get-rn-component "Text"))
(def text-input (get-rn-component "TextInput"))
(def touchable-highlight (get-rn-component "TouchableHighlight"))
(def touchable-opacity (get-rn-component "TouchableOpacity"))
(def view (get-rn-component "View"))
(def web-view (get-rn-component "WebView"))


(defn render-item-wrapper [renderItem]
  (fn [js-item]
    (let [react-item (js->clj js-item :keywordize-keys true)
          item (:item react-item)]
      (r/as-element [renderItem item]))))

(defn flat-list [{:keys [renderItem] :as options}] ;; Ignore children
  "FlatList wrapper component to reduce js interop headache"
  (let [new-options (assoc options :renderItem (render-item-wrapper renderItem))]
    [flatList new-options]))
