(ns expo.core
  (:require [reagent.core :as r]))


; App Loading
(def Expo (js/require "expo"))
(def app-loading (r/adapt-react-class (aget Expo "AppLoading")))
(def map-view (r/adapt-react-class (aget Expo "MapView")))
(def map-marker (r/adapt-react-class (aget (aget Expo "MapView") "Marker")))
(def web-browser (aget Expo "WebBrowser"))

; ICON
(def VectorIcons (js/require "@expo/vector-icons"))
(def icons (r/adapt-react-class (aget VectorIcons "SimpleLineIcons")))
(def fontawesome (r/adapt-react-class (aget VectorIcons "FontAwesome")))
(def ionicons (r/adapt-react-class (aget VectorIcons "Ionicons")))

