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
        component: () => import('@/components/FormComponent')
    },

    {
        path: '/membre/:id/confirmation',
        name: 'Confirmation',
        component: () => import('@/views/RegistrationConfirmation')
    },
    {
        path: '/membres/modifier/:id',
        name: 'Modifier',
        component: () => import('@/views/EditMember')
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;