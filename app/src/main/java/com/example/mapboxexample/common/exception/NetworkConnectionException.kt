package com.example.mapboxexample.common.exception

const val NETWORK_NOT_CONNECTED = "Network not connected"
class NetworkConnectionException : Throwable(NETWORK_NOT_CONNECTED)