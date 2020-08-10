import {action, observable} from "mobx"

class CommonStore {

    @observable loading: Boolean = false
    @observable error: String = null

    @action setLoading(loading: Boolean) {
        this.loading = loading
    }

    @action setError(error: String) {
        this.error = error
    }

    @action clearError() {
        this.error = null
    }
}
export default new CommonStore()