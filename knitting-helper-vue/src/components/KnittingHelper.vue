<template>
    <div>
        <form action="">
            <label for="pattern">Add your pattern here</label>
            <textarea name="pattern" rows="10" cols="30" v-model="pattern">
                </textarea>
            <div class="labeled-pattern">
                <div v-for="(row, index) in labeledPattern" :key="index">
                    {{ row }}
                </div>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            pattern: '',
            labeledPattern: []
        };
    },
    watch: {
        pattern() {
            this.addLabels();
        }
    },
    methods: {

        addLabels() {
            const lines = this.pattern.split('\n');
            let cleanedLines = [];
            let knitAllKnitsFound = false;
            for (let i = 0; i < lines.length; i++) {
                let line = lines[i];
                let cleanLine = '';
                if (!(/^row\s+\d+:/i.test(line))){
                    cleanLine = `Row ${i + 1}: ` + line;

                
                }
                if (/knit (the )?knits and purl (the )?purls/i.test(line)) {
                    knitAllKnitsFound = true;
                    let previousLine = cleanedLines[i - 1] 
                    cleanLine = this.convertRow(previousLine);
                }
                else if (knitAllKnitsFound & i % 2 == 0){
                    let previousLine = cleanedLines[i - 1] 
                    cleanLine = this.convertRow(previousLine);

                }
                cleanedLines.push(cleanLine);
            }
            return cleanedLines;
        },
        convertRow(row) {
            console.log("calling function")
            const index = row.index;
            let aboveRow = this.labeledPattern[index - 1];
            let spacedRow = aboveRow.split(' ');
            console.log(aboveRow);


        }



    }
};
</script>