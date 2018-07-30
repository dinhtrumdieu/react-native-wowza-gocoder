import React, {Component} from 'react';
import PropTypes from 'prop-types'
import {StyleSheet, requireNativeComponent, NativeModules, View, DeviceEventEmitter, Platform} from 'react-native';
import BroadcastView from "react-native-wowza-gocoder";
const BroadcastManager = NativeModules.BroadcastModule;

const styles = StyleSheet.create({
    base: {
        overflow: 'hidden',
    },
});

export default class PlayView extends Component {

    setNativeProps(nativeProps) {
        this._root.setNativeProps(nativeProps);
    }

    _assignRoot = (component) => {
        this._root = component;
    };

    render() {
        const nativeProps = Object.assign({}, this.props);
        return (
            <RNPlayerView
                ref={this._assignRoot}
                {...nativeProps}
            />
        );
    }
}

BroadcastView.propTypes = {
     hostAddress: PropTypes.string.isRequired,
     applicationName: PropTypes.string.isRequired,
     sdkLicenseKey: PropTypes.string.isRequired,
     broadcastName: PropTypes.string.isRequired,
     port: PropTypes.number,
     username: PropTypes.string.isRequired,
     password: PropTypes.string.isRequired,
    isPlay: PropTypes.bool,
     ...View.propTypes,
};

const RNPlayerView = requireNativeComponent('RNPlayerView', PlayView);
