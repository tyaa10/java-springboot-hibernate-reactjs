import React, { Component } from 'react'
import { withRouter } from "react-router-dom"
import {Button, Card, Col, Icon, Row, TextInput} from "react-materialize"
import {inject, observer} from "mobx-react"
import {reaction} from "mobx"

@inject("commonStore", "userStore", "routerStore")
@withRouter
@observer
class SignIn extends Component {

    componentWillUnmount() {
        this.props.userStore.reset()
    }

    handleUserNameChange = e => {
        this.props.userStore.setUserName(e.target.value)
    }

    handlePasswordChange = e => {
        this.props.userStore.setPassword(e.target.value)
    }

    handleSubmitForm = e => {
        e.preventDefault()
        this.props.userStore.login()
    }

    userReaction = reaction(
        () => this.props.userStore.user,
        (user) => {
            if (user) {
                this.props.history.replace("/")
                this.props.routerStore.setLoggedRoutes()
            } else {
                this.props.history.replace("/signin")
                this.props.routerStore.setAnonymousRoutes()
            }
        }
    )

    render () {
        const { loading } = this.props.commonStore
        const { userName, password } = this.props.userStore
        return (
            <Row>
                <Col
                    s={12}
                >
                    <Card
                        className="grey lighten-2"
                        closeIcon={<Icon>close</Icon>}
                        title="Sign In"
                    >
                        <Row>
                            <Col
                                s={12}
                            >
                                <form>
                                    <Row>
                                        <TextInput
                                            s={12}
                                            label='Login'
                                            validate
                                            value={userName}
                                            onChange={this.handleUserNameChange}
                                        />
                                    </Row>
                                    <Row>
                                        <TextInput
                                            s={12}
                                            label='Password'
                                            validate
                                            value={password}
                                            onChange={this.handlePasswordChange}
                                        />
                                    </Row>
                                    <Row>
                                        <Button
                                            node="button"
                                            waves="light"
                                            disabled={loading}
                                            onClick={this.handleSubmitForm}
                                        >
                                            Submit
                                            <Icon right>
                                                send
                                            </Icon>
                                        </Button>
                                    </Row>
                                </form>
                            </Col>
                        </Row>
                    </Card>
                </Col>
            </Row>
        )
    }
}

export default SignIn