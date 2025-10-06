public class Vector {
    double[] data;
    int vectorLength;

    public Vector(double[] data) {
        this.data = data;
        this.vectorLength = this.data.length;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vectorLength; i++) {
            String substr = "| " + data[i] + " |" + System.lineSeparator();
            sb.append(substr);
        }
        return sb.toString();
    }
}
