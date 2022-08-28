<template>
  <div>
    <h3 class="title">Vos informations sont enregistrées !</h3>
    <MemberDetails />
  </div>
</template>
<script>
import MemberDetails from '@/components/MemberDetails'

export default {
  data() {
    return {
    }
  },
  components: {
    MemberDetails
  }
}
</script>
<style>
  .title {
    font-size: 24px;
    color: aliceblue;
    padding: 10px;
  }
</style>
