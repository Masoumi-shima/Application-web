<template>
  <MembersList />
  <div>
    <router-view />
  </div>
</template>

<script>
import MembersList from './views/MembersList'

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
.title {
  font-size: 24px;
  text-align: center;
  color: aliceblue;
  padding: 10px;
}

body, .gender_values {
  font-size: 15px;
  margin: 0px;
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

</style>
