<template>
  <HomeLogo />
  <div>
    <router-view :key="$route.path"/>
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