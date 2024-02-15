import Main from "@/pages/Main.vue";
import WeatherHere from "@/pages/WeatherHere.vue";
import WeatherOtherCity from "@/pages/WeatherOtherCity.vue";
import {createWebHistory} from "vue-router";
import Vue from 'vue'

const Home = { template: '<div>Home</div>' }

const routes = [
    {
        path: '/',
        component: Main
    },
    {
        path: '/weatherHere',
        component: WeatherHere
    },
    {
        path: '/weatherOtherCity',
        component: WeatherOtherCity
    },
]

const router = VueRouter.createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

const app = Vue.createApp({})
app.use(router)
app.mount('#app')
export default router;