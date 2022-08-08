<template>
  <div class="back">
    <router-link to="/">
      <img src="../../main/resources/static/images/home.png" alt="Page d'acceuil" width="70" height="70">
    </router-link>
  </div>
  <MembersList/>
  <div>
    <router-view />
  </div>
</template>

<script>

import MembersList from "@/views/MembersList";
export default {
  name: 'App',
  components: {
    MembersList
  },
  methods: {
  }
}
</script>

<style>

body {
  font-size: 15px;
  font-family: "Lato", sans-serif;
  background-image: linear-gradient(
      to right,
      rgb(0, 0, 0) 0%,
      rgb(50, 53, 56) 100%
  );
  color: aliceblue
}

a {
  text-decoration: none ;
  color: aliceblue ;
}

.back {
  display: block;
  color: aliceblue;
  margin: 20px;
}

</style>
