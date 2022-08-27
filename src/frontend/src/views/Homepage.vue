<template>
  <section>
    <div>
      <router-link to="/membres" class="option">Voir la liste des membres</router-link>
      <router-link to="/ajouterMembre" class="option">Enregistrer un nouveau membre</router-link>
    </div>
  </section>
</template>

<style>

.option {
  display: block ;
  margin-top: 10%;
  font-size: 24px;
  text-align: center;
  color: aliceblue;
  padding: 10px;
  text-decoration: none ;
}
</style>