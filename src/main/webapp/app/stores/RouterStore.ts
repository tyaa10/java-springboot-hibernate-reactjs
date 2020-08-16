import {action, observable, reaction} from "mobx"
import Home from "../components/pages/Home";
import Shopping from "../components/pages/Shopping";
import SignIn from "../components/pages/SignIn";
import SignUp from "../components/pages/SignUp";
import userStore from './UserStore'
import Dashboard from "../components/pages/admin/Dashboard";
import DashboardCategories from "../components/pages/admin/DashboardCategories";
import DashboardProducts from "../components/pages/admin/DashboardProducts";

class RouterStore {

    private anonymousRoutes: Array<object> = [
        { path: '/', name: 'Home', Component: Home },
        { path: '/shopping', name: 'Shopping', Component: Shopping },
        { path: '/signin', name: 'Sign in', Component: SignIn },
        { path: '/signup', name: 'Register', Component: SignUp }
    ]

    private loggedRoutes: Array<object> = [
        { path: '/', name: 'Home', Component: Home },
        { path: '/shopping', name: 'Shopping', Component: Shopping },
        { path: '/auth:out', name: `Sign out`, Component: Home }
    ]

    private adminRoutes: Array<object> = [
        { path: '/', name: 'Home', Component: Home },
        { path: '/shopping', name: 'Shopping', Component: Shopping },
        { path: '/admin', name: 'Dashboard', Component: Dashboard },
        { path: '/admin/categories', name: 'DashboardCategories', Component: DashboardCategories },
        { path: '/admin/products', name: 'DashboardProducts', Component: DashboardProducts },
        { path: '/auth:out', name: `Sign out`, Component: Home }
    ]

    @observable routes: Array<object> = this.anonymousRoutes

    @action setAnonymousRoutes() {
        this.routes = this.anonymousRoutes
    }

    @action setLoggedRoutes() {
        this.routes = this.loggedRoutes
    }

    @action setAdminRoutes() {
        this.routes = this.adminRoutes
    }

    userReaction = reaction(
        () => userStore.user,
        (user) => {
            if (user) {
                const signOutRoute =
                    this.loggedRoutes
                        .find(route => route['name'].includes('Sign out'))
                signOutRoute['name'] = `Sign out (${userStore.user.name})`
            }
        }
    )
}

export default new RouterStore()