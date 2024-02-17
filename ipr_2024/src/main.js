import {createRouter, createWebHistory} from "vue-router";
import {createApp} from 'vue'
import App from './App.vue'
import Central from "@/pages/Central.vue";
import WeatherHere from "@/pages/WeatherHere.vue";
import WeatherOtherCity from "@/pages/WeatherOtherCity.vue";

const router = createRouter({
    routes: [{
        path: '/',
        component: Central,
        name: Central
    },
        {
            path: '/weatherHere',
            component: WeatherHere,
            name: WeatherHere
        },
        {
            path: '/weatherOtherCity',
            component: WeatherOtherCity,
            name: WeatherOtherCity
        },]
    ,
    history: createWebHistory(process.env.BASE_URL)
})

createApp(App)
            .use(router)
            .mount('#app')

