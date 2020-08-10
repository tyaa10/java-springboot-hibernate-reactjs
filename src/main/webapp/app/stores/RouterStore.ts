import {action, observable} from "mobx"
import Home from "../components/pages/Home";
import Shopping from "../components/pages/Shopping";
import SignIn from "../components/pages/SignIn";
import SignUp from "../components/pages/SignUp";

class RouterStore {

    private anonymousRoutes: Array<object> = [
        { path: '/', name: 'Home', Component: Home },
        { path: '/shopping', name: 'Shopping', Component: Shopping },
        { path: '/signin', name: 'Sign in', Component: SignIn },
        { path: '/signup', name: 'Sign up', Component: SignUp }
    ]

    private loggedRoutes: Array<object> = [
        { path: '/', name: 'Home', Component: Home },
        { path: '/shopping', name: 'Shopping', Component: Shopping },
        { path: '/auth:out', name: 'Sign out', Component: Home }
    ]

    @observable routes: Array<object> = this.anonymousRoutes

    @action setAnonymousRoutes() {
        this.routes = this.anonymousRoutes
    }

    @action setLoggedRoutes() {
        this.routes = this.loggedRoutes
    }
}

export default new RouterStore()