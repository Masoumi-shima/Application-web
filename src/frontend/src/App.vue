<template>
  <HomeLogo />
  <div>
    <router-view />
  </div>
</template>

<script>
import HomeLogo from "@/components/HomeLogo";
import Homepage from '@/views/Homepage'

export default {
  name: 'App',
  components: {
    HomeLogo,
    Homepage
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
</style>