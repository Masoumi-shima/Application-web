import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: '/',
        name: 'Homepage',
        component: () => import('@/views/Homepage')
    },
    {
        path: '/membres',
        name: 'MembersList',
        component: () => import('@/views/MembersListView'),
        children: [
            {
        path: '/membres/:id',
        name: 'Membre',
        component: () => import('@/components/MemberDetails')
        }
        ]
    },
    {
        path: '/ajouterMembre/:id?',
        name: 'Ajouter membre',
        component: () => import('@/components/Form')
    },

    {
        path: '/membre/:id/confirmation',
        name: 'ConfirmationMembre',
        component: () => import('@/views/RegistrationConfirmation')
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;