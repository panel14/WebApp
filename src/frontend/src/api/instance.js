import axios from "axios";
import store from '@/store'
import router from "@/router";

const instance = axios.create({
    baseURL:'/api',
    withCredentials: true,
    headers:{
        accept: 'application/json',
    }
})

instance.interceptors.response.use(response => {
    return response
},
    error => {
    if (error.response.status === 500){
        store.dispatch('deleteUser')
        router.push('/login')
    }
        return Promise.reject(error);
    }
)

export default instance