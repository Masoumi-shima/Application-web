import { createRouter, createWebHistory } from "vue-router";

import MemberDetails from '../views/MermberDetails'
const routes = [
    {
        path: '/membre',
        name: 'Membre',
        component: MemberDetails,
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;
