import React, { Component } from 'react'
import {inject, observer} from "mobx-react";
import { withRouter } from "react-router-dom"

@inject("commonStore", "userStore")
@withRouter
@observer
class Home extends Component {
    /* constructor (props) {
        super(props)
    } */
    componentDidMount() {
        if (this.props.match && this.props.match.params.out) {
            this.props.userStore.logout()
        }
    }
    render () {
        return (
            <div>
                <h1>Home Page</h1>
                <div>Home Page Content: {this.props.commonStore.loading ? this.props.commonStore.error : 'no errors'}</div>
            </div>
        )
    }
}

export default Home