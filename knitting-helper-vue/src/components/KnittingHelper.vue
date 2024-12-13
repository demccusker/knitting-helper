<template>
    <div>
        <form action="">
            <label for="pattern">Add your pattern here</label>
            <textarea name="pattern" rows="10" cols="30" v-model="pattern"> 
                </textarea>
<div>{{ pattern }}</div>
        </form>
    </div>
</template>

<script>
export default {
  data() {
    return {
      pattern: '' 
    };
  }
};
</script>