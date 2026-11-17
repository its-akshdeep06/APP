"""
Question 3:
An e-commerce platform maintains a list of product prices.
During a festival sale, the company offers a 20% discount on products costing more than Rs. 1,000.
a) Write a pure function to calculate the discounted price.
b) Use map() to apply the discount to eligible products.
c) Use filter() to identify products costing more than Rs. 1,000.
d) Explain how the pure function avoids modifying the original product list.
"""


# -------------------------------------------------------------
# (a) Write a pure function to calculate the discounted price
# -------------------------------------------------------------
def calculate_discounted_price(price: float) -> float:
    """
    A pure function to compute discounted price.
    - If price > 1000, applies a 20% discount (returns price * 0.80).
    - Otherwise, returns the original price unchanged.
    
    Properties of this Pure Function:
    1. Deterministic: For the same input `price`, it always returns the exact same result.
    2. Side-effect free: It does not read or alter any global or external state.
    """
    if price > 1000:
        return price * 0.80
    return price


def main():
    # Dataset: List of tuples (Product Name, Price in Rs.)
    products = [
        ("Wireless Mouse", 750.0),
        ("Mechanical Keyboard", 2500.0),
        ("USB-C Hub", 950.0),
        ("Gaming Headset", 3200.0),
        ("Laptop Stand", 1200.0),
        ("Mouse Pad", 400.0)
    ]

    print("=" * 65)
    print("QUESTION 3: Pure Functions & E-Commerce Discount Processing")
    print("=" * 65)
    print("Original Products List:")
    for name, price in products:
        print(f"  {name:22}: Rs. {price:,.2f}")

    # -------------------------------------------------------------
    # (c) Use filter() to identify products costing more than Rs. 1,000
    # -------------------------------------------------------------
    eligible_products = list(
        filter(lambda p: p[1] > 1000, products)
    )

    print("\n--- (c) Products Costing More Than Rs. 1,000 (filter) ---")
    for name, price in eligible_products:
        print(f"  {name:22}: Rs. {price:,.2f} (Eligible for 20% discount)")

    # -------------------------------------------------------------
    # (b) Use map() to apply the discount to products
    # -------------------------------------------------------------
    # Applying the pure function calculate_discounted_price via map()
    discounted_catalog = list(
        map(lambda p: (p[0], calculate_discounted_price(p[1])), products)
    )

    print("\n--- (b) Festival Sale Catalog after Applying Discount (map) ---")
    for (name, orig_p), (_, disc_p) in zip(products, discounted_catalog):
        discount_status = f"(Discounted by 20%: -Rs. {orig_p - disc_p:,.2f})" if orig_p != disc_p else "(No discount)"
        print(f"  {name:22}: Rs. {disc_p:,.2f}  {discount_status}")

    # Alternatively, applying map specifically to the filtered eligible items:
    only_eligible_discounted = list(
        map(lambda p: (p[0], calculate_discounted_price(p[1])), eligible_products)
    )
    print("\n  [Only Eligible Products with Discount Applied]:")
    for name, disc_p in only_eligible_discounted:
        print(f"    {name:20}: Rs. {disc_p:,.2f}")

    # -------------------------------------------------------------
    # (d) Explain how the pure function avoids modifying original product list
    # -------------------------------------------------------------
    print("\n--- (d) Verification & Explanation: No Modification of Original List ---")
    print(f"  Is original catalog preserved exactly as before? {products == [('Wireless Mouse', 750.0), ('Mechanical Keyboard', 2500.0), ('USB-C Hub', 950.0), ('Gaming Headset', 3200.0), ('Laptop Stand', 1200.0), ('Mouse Pad', 400.0)]}")
    
    explanation = """
    How the Pure Function Avoids Modifying the Original Product List:
    1. Pass-by-Value Semantics for Primitives:
       The price passed into `calculate_discounted_price(price)` is an immutable
       numeric float. The function performs arithmetic and returns a brand-new 
       float value without mutating any reference.

    2. Non-Destructive Operations:
       The function does not modify lists in-place (no `.append()`, `del`, 
       or index assignments like `products[i] = ...`).

    3. Generation of a New Collection:
       The higher-order function `map()` consumes the iterable and constructs 
       a completely new iterator/list (`discounted_catalog`). The original list 
       `products` remains untouched in memory, ensuring referential transparency.
    """
    print(explanation.strip())
    print("=" * 65)


if __name__ == "__main__":
    main()
