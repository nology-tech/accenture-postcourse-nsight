import { customRender } from "../../utils/testUtils";
import AddForm from "./AddForm";

export default describe("Form", () => {
  it("Renders", () => {
    const { container } = customRender(<AddForm />);
    expect(container).toMatchSnapshot();
  });
});
