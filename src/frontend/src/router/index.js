import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: '/membre/:id',
        name: 'Membre',
        component: () => import('../components/MemberDetails')
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;
