import React, { Component } from 'react'
import {inject, observer} from "mobx-react";

@inject("CommonStore")
@observer
class Home extends Component {
    constructor (props) {
        super(props)
    }
    render () {
        console.log(this.props.CommonStore)
        return (
            <div>
                <h1>Home Page</h1>
                <div>Home Page Content: {this.props.CommonStore.loading ? this.props.CommonStore.error : 'no errors'}</div>
            </div>
        )
    }
}

export default Home