<template>
    <div>
        <form action="">
            <label for="pattern">Add your pattern here: </label>
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
                
                if (/knit (all) (the )?knits and purl (all) (the )?purls/i.test(line)) {
                    knitAllKnitsFound = true;
                    let previousLine = cleanedLines[i - 1]
                    cleanLine = this.convertRow(previousLine);
                }
                else if (knitAllKnitsFound && i % 2 == 1 && i > 0) {
                    let previousLine = cleanedLines[i - 1]
                    cleanLine = this.convertRow(previousLine);

                }
                else {
                    cleanLine = cleanLine || line;
                }
                if (!(/^row\s+\d+:/i.test(cleanLine))) {
                    cleanLine = `Row ${i + 1}: ` + cleanLine;


                }
                cleanedLines.push(cleanLine);
            }
            this.labeledPattern = cleanedLines;
        },
        convertRow(row) {
            console.log("calling function on row: ", row)
            let newRow = '';
            let spacedRow = row.split(" ");
            for (let i = spacedRow.length; i > 0; i--) {
                let match = /[kp](\d+)/.exec(spacedRow[i]);
                console.log(match);
                if (match) {
                    let stitchType = match[0].charAt(0);
                    console.log(stitchType);
                    let number = parseInt(match[1], 10);

                    if (stitchType == "k") {
                        newRow = newRow + "p" + number + ", ";

                    }
                    else {
                        newRow = newRow + "k" + number + ", ";
                    }

                }
            }
            return newRow;


        }



    }
};
</script>