import {ReactNode, HTMLAttributes} from "react";
import clsx from "clsx";

const variantsMapping = {
  h1: "h1",
  h2: "h2",
  xs: "p",
  small: "p",
  medium: "p",
  large: "p",
  largeExtra: "p",
  mediumExtra: "p",
} as const;

const variantsStyle = {
  h1: "text-[50px] leading-[80px] tablet:text-[35px] tablet:leading-[46px] phone:text-[20px] phone:leading-[26px]",
  h2: "text-[40px] leading-[54px] phone:text-[28px] phone:leading-[40px]",
  xs: "text-[12px] leading-[14px]",
  small: "text-[14px] leading-[22px]",
  medium: "text-[16px] leading-[26px] phone:text-[16px] leading-[24px]",
  large: "text-[24px] phone:text-[16px] leading-[29px] phone:leading-[20px]",
  largeExtra:
    "text-[48px] phone:text-[32px] leading-[72px] phone:leading-[40px]",
  mediumExtra: "text-[32px] leading-[29px] phone:text-[24px]",
};

type Variant = keyof typeof variantsMapping;

interface TypographyProps extends HTMLAttributes<HTMLElement> {
  variant?: Variant;
  color?: string;
  fontWeight?: string;
  className?: string;
  children?: ReactNode;
  href?: string;
}

function Typography({
                      variant,
                      color,
                      fontWeight,
                      className,
                      children,
                      href,
                      ...props
                    }: TypographyProps) {
  const Component = href ? "a" : variant ? variantsMapping[variant] : "p";

  return (
    <Component
      className={clsx(
        className,
        "break-words",
        variant && variantsStyle[variant],
        color && `text-${color}`,
        fontWeight && `font-${fontWeight}`,
        href && "cursor-pointer"
      )}
      {...props}
      href={href}
    >
      {children}
    </Component>
  );
}

export default Typography;
