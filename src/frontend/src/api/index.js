import instance from './instance'
import authModule from "./auth";

export default {
    auth: authModule(instance)
}