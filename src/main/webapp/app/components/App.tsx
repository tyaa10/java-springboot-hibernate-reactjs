import React, { Component } from 'react'
import {Container, Icon, Navbar} from 'react-materialize'
import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min'
import './style.css'
import {
    Router,
    Route,
    NavLink
} from 'react-router-dom'
import { CSSTransition } from 'react-transition-group'
import {inject, observer} from "mobx-react";
import {reaction} from "mobx";
import history from '../history'

@inject("routerStore", "userStore")
@observer
class App extends Component {

    userReaction = reaction(
        () => this.props.userStore.user,
        (user) => {
            if (user) {
                history.replace("/")
                if (user.roleName === 'ROLE_ADMIN') {
                    this.props.routerStore.setAdminRoutes()
                } else {
                    this.props.routerStore.setLoggedRoutes()
                }
            } else {
                history.replace("/signin")
                this.props.routerStore.setAnonymousRoutes()
            }
        }
    )

    render () {
        const { routes } = this.props.routerStore
        return <Router history={history} >
            <div>
                <Navbar
                    alignLinks="right"
                    menuIcon={<Icon>menu</Icon>}
                    brand={<a className="brand-logo" href="#">SimpleSPA</a>}
                    id="mobile-nav"
                    options={{
                        draggable: true,
                        edge: 'left',
                        inDuration: 250,
                        onCloseEnd: null,
                        onCloseStart: null,
                        onOpenEnd: null,
                        onOpenStart: null,
                        outDuration: 200,
                        preventScrolling: true
                    }}
                >
                    {routes.map(route => {
                        if(!/^Dashboard[A-z]+$/.test(route.name)) {
                            return <NavLink
                                key={route.path}
                                as={NavLink}
                                to={route.path}
                                activeClassName="active"
                                exact

                            >
                                {route.name}
                            </NavLink>
                        } else {
                            return ''
                        }
                    })}
                </Navbar>
                <Container>
                    {routes.map(({ path, Component }) => (
                        <Route key={path} exact path={path}>
                            {({ match }) => (
                                <CSSTransition
                                    in={match != null}
                                    timeout={300}
                                    classNames="page"
                                    unmountOnExit
                                >
                                    <div className="page">
                                        <Component />
                                    </div>
                                </CSSTransition>
                            )}
                        </Route>
                    ))}
                </Container>
            </div>
        </Router>
    }
}

export default App